package com.whatwapp.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whatwapp.gateway.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	
	Room findByTabela(int tabela);

}
