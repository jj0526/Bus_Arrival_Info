package kr.co.company.bus_arrival_info;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import kr.co.company.bus_arrival_info.controller.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class MainActivityButtonTest {

    @Test
    public void testButtonClick() {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        // 버튼 클릭 이벤트를 테스트
        Espresso.onView(ViewMatchers.withId(R.id.search_button)).perform(click());

        activityScenario.close();
    }
}
