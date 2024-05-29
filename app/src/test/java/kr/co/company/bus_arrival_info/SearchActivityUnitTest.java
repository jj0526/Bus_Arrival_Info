package kr.co.company.bus_arrival_info;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

import kr.co.company.bus_arrival_info.controller.SearchActivity;

public class SearchActivityUnitTest {

    @Mock
    private SearchActivity searchActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNearBusGPS() throws IOException {
        // 테스트할 인자 설정
        String tmX = "127.100079";
        String tmY = "37.513326";
        String radius = "200";

        doAnswer(invocation -> {
            System.out.println("호출되었습니다");
            return null;
        }).when(searchActivity).nearBusGPS(tmX, tmY, radius);

        searchActivity.nearBusGPS(tmX, tmY, radius);

        verify(searchActivity, times(1)).nearBusGPS(tmX, tmY, radius);
    }
}
