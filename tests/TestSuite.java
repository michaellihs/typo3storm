import com.punktde.typo3storm.Typo3StormSettingsTests;
import com.punktde.typo3storm.util.Typo3StormStringUtilsTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests in typo3storm plugin
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {
        Typo3StormStringUtilsTests.class,
        Typo3StormSettingsTests.class
} )

public class TestSuite {
}
