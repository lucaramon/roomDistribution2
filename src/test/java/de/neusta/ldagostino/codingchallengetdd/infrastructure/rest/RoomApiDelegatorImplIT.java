package de.neusta.ldagostino.codingchallengetdd.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neusta.ldagostino.codingchallengetdd.application.RoomService;
import de.neusta.ldagostino.codingchallengetdd.domain.Room;
import de.neusta.ldagostino.codingchallengetdd.generated.api.RoomApi;
import de.neusta.ldagostino.codingchallengetdd.generated.api.RoomApiController;
import de.neusta.ldagostino.codingchallengetdd.generated.model.RoomDto;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.ErrorHandler;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.ErrorMessage;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.RoomApiDelegatorImpl;
import de.neusta.ldagostino.codingchallengetdd.infrastructure.rest.RoomDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(RoomApi.class)
@Import({ErrorHandler.class, RoomApiController.class, RoomApiDelegatorImpl.class})
public class RoomApiDelegatorImplIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RoomService roomService;

    @MockBean
    RoomDtoMapper roomDtoMapper;


    @Test
    public void getExistingRoomMockMvcTest() throws Exception {

        Room room = new Room("1234");
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNumber("1234");

        Mockito.when(roomService.getRoom("1234")).thenReturn(room);
        Mockito.when(roomDtoMapper.mapRoomToRoomDto(room)).thenReturn(roomDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getNotExistingRoomMockMvcTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/room/1234").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        ErrorMessage errorMessage = objectMapper.readValue(contentAsString, ErrorMessage.class);

        Assertions.assertThat(errorMessage.getCode()).isEqualTo(5);
        Assertions.assertThat(errorMessage.getMessage()).isEqualTo("Raum mit Nummer 1234 nicht gefunden!");
    }

    @Test
    public void getRoomWithInvalidRoomNumberMockMvcTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/room/12345").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        ErrorMessage errorMessage = objectMapper.readValue(contentAsString, ErrorMessage.class);

        Assertions.assertThat(errorMessage.getCode()).isEqualTo(6);
        Assertions.assertThat(errorMessage.getMessage()).isEqualTo("Die Raumnummer muss 4 stellig sein!");
    }

    @Test
    public void getExistingRoomCollectionMvcTest() throws Exception {

        Room room = new Room("1234");

        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNumber("1234");

        List<Room> allRooms = List.of(room);

        Mockito.when(roomService.getRooms()).thenReturn(allRooms);
        Mockito.when(roomDtoMapper.mapRoomsToRoomDto(allRooms)).thenReturn(List.of(roomDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getNotExistingRoomCollectionMockMvcTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/room").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        ErrorMessage errorMessage = objectMapper.readValue(contentAsString, ErrorMessage.class);

        Assertions.assertThat(errorMessage.getCode()).isEqualTo(5);
        Assertions.assertThat(errorMessage.getMessage()).isEqualTo("Es wurde kein Raum gefunden!");
    }
}