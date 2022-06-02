import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CiudadTest {

	private Ciudad c;

	void setupScenario1() {
		c = new City("Cali");
	}

	@Test
	void test() {
		int a = c.hashCode();
		System.out.println(a);
	}

}