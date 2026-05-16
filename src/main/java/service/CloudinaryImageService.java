package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import controller.signupcontroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import Exception.ImageUploadFailed;
import Exception.InvalidImageFile;


public class CloudinaryImageService {
	private static final String DEFAULT_FOLDER = "profiles";
	private static final String SECRETS_FILE_NAME = "secreats.properties";
	private final Cloudinary cloudinary;
	private final String folder;
	private static final Logger logger =
			LoggerFactory.getLogger(CloudinaryImageService.class);

	public CloudinaryImageService() {
		logger.info("Loading cloudflare properties");
		Properties properties = loadSecrets();
		String cloudName = getRequiredConfig(properties, "cloudinary.cloud_name", "CLOUDINARY_CLOUD_NAME");
		String apiKey = getRequiredConfig(properties, "cloudinary.api_key", "CLOUDINARY_API_KEY");
		String apiSecret = getRequiredConfig(properties, "cloudinary.api_secret", "CLOUDINARY_API_SECRET");
		logger.info("Cloudflare proerty = "+ cloudName);
		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret,
				"secure", true));
		String configuredFolder = getOptionalConfig(properties, "cloudinary.profile_folder", "CLOUDINARY_PROFILE_FOLDER");
		this.folder = configuredFolder == null || configuredFolder.trim().isEmpty()
				? DEFAULT_FOLDER
				: configuredFolder.trim();
	}

	private Properties loadSecrets() {
		Properties properties = new Properties();

		try (InputStream classpathStream =
				CloudinaryImageService.class.getClassLoader().getResourceAsStream(SECRETS_FILE_NAME)) {
			if (classpathStream != null) {
				properties.load(classpathStream);
				return properties;
			}
		} catch (IOException exception) {
			// Fall back to environment variables when the local file is not present or unreadable.
		}

		return properties;
	}

	private String getRequiredConfig(Properties properties, String propertyKey, String envKey) {
		String value = properties.getProperty(propertyKey);
		if (value == null || value.trim().isEmpty()) {
			value = System.getenv(envKey);
		}
		if (value == null || value.trim().isEmpty()) {
			throw new ImageUploadFailed("Missing Cloudinary configuration for " + propertyKey + " in " + SECRETS_FILE_NAME);
		}
		return value.trim();
	}

	private String getOptionalConfig(Properties properties, String propertyKey, String envKey) {
		String value = properties.getProperty(propertyKey);
		if (value == null || value.trim().isEmpty()) {
			value = System.getenv(envKey);
		}
		return value == null ? null : value.trim();
	}

	public String uploadProfileImage(MultipartFile file, String email) {
		if (file == null || file.isEmpty()) {
			throw new InvalidImageFile("Please choose an image file to upload.");
		}

		String contentType = file.getContentType();
		if (contentType == null || !contentType.toLowerCase().startsWith("image/")) {
			throw new InvalidImageFile("Only image files are allowed for profile upload.");
		}

		try {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(
					file.getBytes(),
					ObjectUtils.asMap(
							"folder", folder,
							"public_id", buildPublicId(email),
							"overwrite", true,
							"invalidate", true,
							"resource_type", "image"));
			Object secureUrl = uploadResult.get("secure_url");
			if (secureUrl == null || secureUrl.toString().trim().isEmpty()) {
				throw new ImageUploadFailed("Cloudinary did not return an image URL.");
			}
			return secureUrl.toString().trim();
		} catch (IOException exception) {
			throw new ImageUploadFailed("We could not read the uploaded image file.");
		} catch (ImageUploadFailed exception) {
			throw exception;
		} catch (Exception exception) {
			throw new ImageUploadFailed("We could not upload the profile image right now.");
		}
	}

	private String buildPublicId(String email) {
		return email.trim().toLowerCase().replaceAll("[^a-z0-9]+", "_");
	}
}
