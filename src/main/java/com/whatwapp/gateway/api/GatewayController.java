package com.whatwapp.gateway.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.whatwapp.gateway.dto.UserDTO;
import com.whatwapp.gateway.dto.UserList;
import com.whatwapp.gateway.entity.Room;
import com.whatwapp.gateway.service.MatchService;

@RestController
@RequestMapping("/api/v1/match")
public class GatewayController {

	private static final Logger logger =  LoggerFactory.getLogger(GatewayController.class.getName());
	public static final String MATCHMAKING_SERVICE = "http://localhost:8081/api/v1/users";
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
    private MatchService matchService;
	
	@GetMapping()
    public ResponseEntity<List<Room>> getAll() {
		
		return new ResponseEntity<List<Room>>(matchService.getAllMatches(), HttpStatus.OK);
	}
		
	
	@GetMapping("/{user}/{legaue}/{tabela}")
	public ResponseEntity<String> matchLevels(@PathVariable String user, @PathVariable String legaue, @PathVariable String tabela) {
		int result = 0;
		if (Integer.valueOf(legaue) > 0 && Integer.valueOf(legaue) < 3 ) {
			Room room = matchService.getByLegaueTabela(Integer.valueOf(tabela));
			if(room != null) {
				result = room.getDuel();
			}
			
		}
		return new ResponseEntity<String>(String.valueOf(result), HttpStatus.OK);
	}

	@GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
		logger.info("Get sorted users from matchmaking service");
		
//		ResponseEntity<List<UserDTO>> responseEntity =
//		        restTemplate.exchange(USER_SERVICE,
//		            HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {
//		            });
//		List<UserDTO> usersList = responseEntity.getBody();
		
		UserList users = restTemplate.getForObject(MATCHMAKING_SERVICE, UserList.class);
		
        return new ResponseEntity<List<UserDTO>>(users.getUsers(), HttpStatus.OK);

    }
	
	@GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
		logger.info("Get user with id: " + id + " from matchmaking service");
		UserDTO user = restTemplate.getForObject(MATCHMAKING_SERVICE + "/" + id , UserDTO.class);
        return (user != null) ? new ResponseEntity<UserDTO>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody(required = true) UserDTO userDTO) {
		logger.info("Create new user: " + userDTO.toString() + "user matchmaking save method");
		ResponseEntity<String> result = restTemplate.postForEntity(MATCHMAKING_SERVICE, userDTO, String.class);
        return (result.getStatusCode().equals(201)) ? new ResponseEntity<>(userDTO, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
}
