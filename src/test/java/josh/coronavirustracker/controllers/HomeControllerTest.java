package josh.coronavirustracker.controllers;

import josh.coronavirustracker.models.DataModel;
import josh.coronavirustracker.services.CoronavirusDataService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private Model model;

    @Mock
    private CoronavirusDataService coronavirusDataService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        doReturn(new ArrayList<>()).when(coronavirusDataService).getDataList();
        doReturn(model).when(model).addAttribute(anyString(), any());

        String home = homeController.home(model);

        assertEquals("home", home);
    }

    @Test
    void canGetHome() {
    }
}