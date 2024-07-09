//package med.voll.api.controller;
//
//import med.voll.api.domain.consultation.BookConsultationService;
//import med.voll.api.domain.consultation.DataBookConsultation;
//import med.voll.api.domain.consultation.DataConsultationDetails;
//import med.voll.api.domain.mdData.Speciality;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//class ConsultationControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private JacksonTester<DataBookConsultation> dataBookConsultationJacksonTester;
//    @Autowired
//    private JacksonTester<DataConsultationDetails> dataConsultationDetailsJacksonTester;
//    @MockBean
//    private BookConsultationService bookConsultationService;
//
//    @Test
//    @DisplayName("Must return status 400 when data is invalid")
//    @WithMockUser
//    void bookScenario1() throws Exception {
//        var response = mvc.perform(post("/consultations")).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//
//    }
//
//    @Test
//    @DisplayName("Must return status 200 when data is valid")
//    @WithMockUser
//    void bookScenario2() throws Exception {
//        var date = LocalDateTime.now().plusHours(1);
//        var speciality = Speciality.CARDIOLOGY;
//
//        when(bookConsultationService.book(any())).thenReturn(new DataConsultationDetails(null, 2l, 5l, date));
//
//        var response = mvc.perform(post("/consultations")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(dataBookConsultationJacksonTester.write(new DataBookConsultation(2l,5l, date, speciality)).getJson())
//        ).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//
//        var expectedJson = dataConsultationDetailsJacksonTester.write(new DataConsultationDetails(null, 2l,5l,date)).getJson();
//
//        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
//    }
//}