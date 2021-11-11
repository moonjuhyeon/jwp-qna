package subway.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LineTest {

	@Autowired
	LineRepository lines;

	@Autowired
	StationRepository stations;
	private LineRepository lines1;

	@Test
	void saveWithLine() {
		//given
		final Station station1 = new Station("잠실역");
		station1.setLine(lines.save(new Line("2호선")));

		//when
		final Station station2 = stations.save(station1);
		stations.flush();

		//then
		assertThat(station2.getLine().getName()).isEqualTo("2호선");
	}

	@Test
	void findByNameWithLine() {
		//given //when
		final Station actual = stations.findByName("교대역");

		//then
		assertThat(actual).isNotNull();
		assertThat(actual.getLine().getName()).isEqualTo("3호선");
	}

	@Test
	void updateWithLine() {
		//given
		final Station expected = stations.findByName("교대역");

		//when
		expected.setLine(lines.save(new Line("2호선")));
		stations.flush();

		//then
		assertThat(expected.getLine().getName()).isEqualTo("2호선");
	}

	@Test
	void removeLine() {
		//given
		final Station expected = stations.findByName("교대역");

		//when
		expected.setLine(null);
		stations.flush();

		//then
		assertThat(expected.getLine()).isNull();
	}

	@Test
	void findByName() {
		//given //when
		final Line line = lines.findByName("3호선");

		//then
		assertThat(line.getStations()).hasSize(1);
	}

	@Test
	void save() {
		//given
		final Line expect = lines.save(new Line("2호선"));
		expect.addStation(stations.save(new Station("잠실역")));

		//when //then
		lines.save(expect);
		lines.flush();
	}
}