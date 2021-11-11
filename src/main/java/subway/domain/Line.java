package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "line")
	private List<Station> stations = new ArrayList<>();

	protected Line() {

	}

	public Line(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Line line = (Line)o;
		return Objects.equals(id, line.id) && Objects.equals(name, line.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	public List<Station> getStations() {
		return stations;
	}

	public void addStation(final Station station) {
		station.setLine(this);
		stations.add(station);
	}
}
