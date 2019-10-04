package api.tests;

import java.util.ArrayList;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.api.test.Utils;

public class APITest {

	SoftAssert sa = new SoftAssert();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// test to check api response
		Utils utils = new Utils();

		String json = utils
				.jsonResponseAsString("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");

		Map<String, Object> jsonPropertiesMap = utils.mapForJson(json);

		// Test the response values

		// Test whether Name equals Carbon credits
		String name = (String) jsonPropertiesMap.get("Name");
		sa.assertEquals(name, "Carbon credits");

		// Test whether CanRelist is true
		boolean canRelist = (boolean) jsonPropertiesMap.get("CanRelist");
		sa.assertEquals(canRelist, true);

		// Test whether Promotions element with Name = "Gallery" has a Description that
		// contains the text "2x larger image"
		// Promotions key in response holds array of maps as value, we will check the
		// same for validating given criteria
		ArrayList<Map<String, String>> listOfMaps = (ArrayList<Map<String, String>>) jsonPropertiesMap
				.get("Promotions");
		int checkAllConditions = 0;
		for (Map<String, String> map : listOfMaps) {
			for (Map.Entry ent : map.entrySet()) {
				if (ent.getKey().equals("Name") && ent.getValue().equals("Gallery")) {
					checkAllConditions++;
				}
				if (ent.getKey().equals("Description") && ent.getValue().toString().contains("2x larger image")) {
					checkAllConditions++;
				}
			}
			if (checkAllConditions == 2)
				break;
		}
		sa.assertEquals(checkAllConditions, 2);
		sa.assertAll();
	}
}
