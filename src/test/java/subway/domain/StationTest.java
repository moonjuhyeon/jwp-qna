package subway.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StationTest {

	@Autowired
	StationRepository stations;

	@Test
	void name() {
		//given
		final Station station = new Station("잠실역");

		//when
		final Station saveStation = stations.save(station);

		//then
		assertThat(saveStation.getId()).isNotNull();
		assertThat(saveStation.getName()).isEqualTo("잠실역");
	}

	@Test
	void findByName() {
		//given
		final Station station1 = stations.save(new Station("잠실역"));

		//when
		final Station station2 = stations.findByName(station1.getName());

		//then
		assertThat(station1.getName()).isEqualTo(station2.getName());
		assertThat(station2.equals(station1)).isTrue();
		assertThat(station2).isSameAs(station1);
	}

	@Test
	void findById() {
		//given
		final Station station1 = stations.save(new Station("잠실역"));

		//when
		final Station station2 = stations.findById(station1.getId()).get();

		//then
		assertThat(station2).isSameAs(station1);
	}

	@Test
	void update() {
		//given
		final Station station1 = stations.save(new Station("잠실역"));
		station1.changeName("몽촌토성역");
		station1.changeName("잠실역");
		station1.changeName("몽촌토성역");

		//when
		final Station station2 = stations.findByName("몽촌토성역");

		//then
		assertThat(station2).isNotNull();
	}
}