package kr.co.company.bus_arrival_info;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import kr.co.company.bus_arrival_info.controller.MainActivity;
import kr.co.company.bus_arrival_info.controller.SearchActivity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
public class MainActivityButtonTest {

    @Before
    public void setUp() {
        // Espresso Intents 초기화
        Intents.init();
    }

    @After
    public void tearDown() {
        // Espresso Intents 해제
        Intents.release();
    }

    @Test
    public void testButtonClick() {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        // 버튼 클릭 이벤트를 테스트
        Espresso.onView(ViewMatchers.withId(R.id.search_button)).perform(click());

        // SearchActivity로 인텐트가 발생했는지 확인
        intended(hasComponent(SearchActivity.class.getName()));

        activityScenario.close();
    }
}
