package com.example.hotel.services;

import com.example.hotel.dtos.ReservationDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.*;
import com.example.hotel.repositories.ReservationRepository;
import com.example.hotel.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    RoomTypePriceService roomTypePriceService;

    public Reservation makeReservation(ReservationDto reservationDto) throws ErrorException {
//        log.info("reservationDto::{}", reservationDto);

        HotelRoom room = roomService.findById(reservationDto.getRoomId());
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDto, reservation);
//        log.info("reservation1::{}", reservation);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        reservation.setArrivalDate(LocalDateTime.parse(reservationDto.getArrivalDate(), formatter));
        reservation.setDepartureDate(LocalDateTime.parse(reservationDto.getDepartureDate(), formatter));
        // find the number of nights and save it to the reservation
        Long nights = ChronoUnit.DAYS.between(reservation.getArrivalDate().toLocalDate(), reservation.getDepartureDate().toLocalDate());
        reservation.setNumberOfNights(nights);
        // calculate the amount to be paid based on the number of nights and price of room
        Double amount = roomTypePriceService.getRoomPrice(room.getRoomType().name());
        reservation.setTotalAmount(amount*nights);
//        log.info("reservation::{}", reservation);
//        Reservation finalReservation = reservationRepository.save(reservation);
//        log.info("finalReservation::{}", reservation);
        if (reservation.getPaymentStatus().equals(PaymentStatus.PENDING)){
            reservation.setMessage("Please note that rooms are not booked until payment is made.");
//            finalReservation.setRoom(room);
            return reservationRepository.save(reservation);
        }
        // book room if status is paid
        room.setRoomStatus(RoomStatus.BOOKED);
        roomRepository.save(room);
        reservation.setRoom(room);
        reservation.setMessage("Room booked successfully!");
        return reservationRepository.save(reservation);
    }

//    public Reservation makeReservation(Reservation reservation) throws ErrorException {
//        // check if rooms are available for the room type chosen
//        List<HotelRoom> rooms = roomService.getRoomsByType(reservation.getRoomType());
//        if (rooms.isEmpty()){
//            throw new ErrorException("No " + reservation.getRoomType().name() + " room");
//        }
//        // get the number of available rooms and add it to an array list
//        List<HotelRoom> hotelRoomsAvailable = new ArrayList<>();
//        long availableRooms = 0L;
//        for (HotelRoom room: rooms){
//            if (room.getRoomStatus().name().equals("AVAILABLE")){
//                availableRooms += 1;
//                hotelRoomsAvailable.add(room);
//            }
//        }
//        // check if the number of available rooms is lesser than rooms to be reserved
//        if (availableRooms < reservation.getNumberOfRooms()){
//            throw new ErrorException("Only " + availableRooms + " rooms available");
//        }
//        // save the reservation if the number is greater
//        Reservation newReservation = reservationRepository.save(reservation);
//        // find the number of nights and save it to the reservation
//        Long nights = ChronoUnit.DAYS.between(newReservation.getArrivalDate().toLocalDate(), newReservation.getDepartureDate().toLocalDate());
//        newReservation.setNumberOfNights(nights);
//        // calculate the amount to be paid based on the number of nights and price of room
//        Double amount = roomTypePriceService.getRoomPrice(reservation.getRoomType().name());
//        newReservation.setTotalAmount(amount*nights);
//        Reservation finalReservation = reservationRepository.save(newReservation);
//        // check if payment has been made
//        List<HotelRoom> roomsBooked = new ArrayList<>();
//        if (finalReservation.getPaymentStatus().name().equals("PENDING")){
//            finalReservation.setMessage("Please note that rooms are not booked until payment is made.");
//            finalReservation.setRooms(roomsBooked);
//            return reservationRepository.save(finalReservation);
//        }
//        // book room if status is paid
//        for (int i=0; i<reservation.getNumberOfRooms(); i++){
//            HotelRoom bookedRoom = hotelRoomsAvailable.get(i);
//            roomsBooked.add(bookedRoom);
//            bookedRoom.setRoomStatus(RoomStatus.BOOKED);
//            roomRepository.save(bookedRoom);
//        }
//        finalReservation.setRooms(roomsBooked);
//        finalReservation.setMessage("Rooms booked successfully!");
//        return reservationRepository.save(finalReservation);
//    }

    public Reservation getReservationById(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()){
            return null;
        }
        return reservation.get();
    }

    public List<Reservation> getReservationByUserId(Long userId) {
        return reservationRepository.findByUserIdAndActive(userId, true);
    }

    public HotelRoom checkOut(String roomCode) throws ErrorException{
        HotelRoom room = roomService.getRoom(roomCode);
        room.setRoomStatus(RoomStatus.AVAILABLE);
        return roomRepository.save(room);
    }
}
