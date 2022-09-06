package com.whatwapp.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatwapp.gateway.entity.Room;
import com.whatwapp.gateway.repository.RoomRepository;

@Service
public class MatchService {

	@Autowired
	private RoomRepository roomRepository;
	
	public List<Room> getAllMatches() {
        return roomRepository.findAll();
    }
	
	public Room getByLegaueTabela(int tabela) {
		return roomRepository.findByTabela(tabela);
	}
	
}
