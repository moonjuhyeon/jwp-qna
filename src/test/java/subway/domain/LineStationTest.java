package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LineStationTest {

	@Autowired
	LineStationRepository lineStations;

	@Autowired
	StationRepository stations;

	@Test
	void saveWithLineStation() {
		LineStation lineStation = lineStations.save(new LineStation());
		stations.save(new Station("잠실역", lineStation));
	}

}