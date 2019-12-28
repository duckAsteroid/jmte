package com.floreysoft.jmte;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BooleanIfRendererTest.class, ErrorReportingTest.class, EngineTest.class, MiniParserTest.class,
		HelperTest.class })
public class AllTests {

}