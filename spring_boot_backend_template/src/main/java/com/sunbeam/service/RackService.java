package com.sunbeam.service;

public interface RackService {
    RackDTO addRack(RackDTO dto);
    RackDTO updateRack(Long rackId, RackDTO dto);
    List<RackDTO> getAllRacks();
    List<BookCopyDTO> getBooksByRack(Long rackId);
}
