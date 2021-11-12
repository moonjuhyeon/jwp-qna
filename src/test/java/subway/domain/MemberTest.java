package subway.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberTest {

	@Autowired
	FavoriteRepository favorites;

	@Autowired
	MemberRepository members;

	@Test
	void save() {
		//given
		final Member expected = new Member("jason");
		expected.addFavorite(favorites.save(new Favorite()));

		//when
		final Member actual = members.save(expected);
		members.flush(); // transaction commit

		//then
		assertThat(actual.equals(expected)).isTrue();
	}
}