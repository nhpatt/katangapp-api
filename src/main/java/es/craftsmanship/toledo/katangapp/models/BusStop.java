package es.craftsmanship.toledo.katangapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import es.craftsmanship.toledo.katangapp.business.UnreferenceablePointException;

/**
 * This class represents a bus stop, identified by the route which it belongs,
 * the order of the bus stop in the route, its latitude and longitude
 * coordinates, and its human address.
 *
 * Latitude and Longitude coordinates will represent the <code>Point</code>
 * object of the application model.
 *
 * When serializing to JSON, it will ignore the Point representing the bus stop,
 * and will order the fields alphabetically.
 *
 * @author mdelapenya
 *
 * @see Point
 */
@JsonPropertyOrder(alphabetic = true)
public class BusStop implements ReferenceablePoint {

	public BusStop() {
	}

	public BusStop(
		String routeId, String id, String order, double latitude,
		double longitude, String address) {

		this.address = address;
		this.routeId = routeId;
		this.order = order;
		this.id = id;
		this.point = new Point(latitude, longitude);
	}

	/**
	 * Delegates the distance calculation to the point.
	 *
	 * @param to the Point to calculate the distance
	 *
	 * @return the distance in meters
	 *
	 * @throws UnreferenceablePointException when the <code>to</code> point is
	 *                                       not referenced or is null.
	 */
	public double distanceTo(ReferenceablePoint to)
		throws UnreferenceablePointException {

		return point.distanceTo(to);
	}

	public String getAddress() {
		return address;
	}

	public String getRouteId() {
		return routeId;
	}

	public String getOrder() {
		return order;
	}

	public String getId() {
		return id;
	}

	@Override
	public double getLatitude() {
		return getPoint().getLatitude();
	}

	@Override
	public double getLongitude() {
		return getPoint().getLongitude();
	}

	public Point getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "routeId: " + routeId + ", id: " + id + ", order: " + order +
			", " + point + ", address:" + address;
	}

	public void setCoordinates(double latitude, double longitude) {
		if (point == null) {
			point = new Point(latitude, longitude);
		}
		else {
			point.setLatitude(latitude);
			point.setLongitude(longitude);
		}
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	private String routeId;

	private String order;

	private String id;

	private String address;

	@JsonIgnore
	private Point point;

}
