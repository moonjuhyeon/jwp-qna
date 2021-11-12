package subway.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "line_id")
	private Line line;

	@OneToOne(mappedBy = "station")
	private LineStation lineStation;

	protected Station() {
	}

	public Station(final String name) {
		this.name = name;
	}

	public Station(String name, LineStation lineStation) {
		this.name = name;
		this.lineStation = lineStation;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(final Line line) {
		if (Objects.nonNull(this.line)) {
			this.line.getStations().remove(this);
		}
		this.line = line;
		line.getStations().add(this);
	}

	public LineStation getLineStation() {
		return lineStation;
	}

	public void changeName(final String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Station station = (Station)o;
		return Objects.equals(id, station.id) && Objects.equals(name, station.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
