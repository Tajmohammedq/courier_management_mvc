package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tracking")
public class TrackingNumberProvider {
	@Override
	public String toString() {
		return "TrackingNumberProvider [trackingid=" + trackingid + "]";
	}

	@Id
	private int trackingid;

	public int getTrackingid() {
		return trackingid;
	}

	public void setTrackingid(int trackingid) {
		this.trackingid = trackingid;
	}

	public TrackingNumberProvider() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrackingNumberProvider(int trackingid) {
		super();
		this.trackingid = trackingid;
	}

}
