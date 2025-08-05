package com.sunbeam.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RackServiceImpl implements RackService {

    @Autowired
    private RackDao rackDao;
    @Autowired
    private BookCopyDao bookCopyDao;
    @Autowired
    private ModelMapper mapper;

    @Override
    public RackDTO addRack(RackDTO dto) {
        Rack rack = mapper.map(dto, Rack.class);
        return mapper.map(rackDao.save(rack), RackDTO.class);
    }

    @Override
    public RackDTO updateRack(Long rackId, RackDTO dto) {
        Rack rack = rackDao.findById(rackId).orElseThrow();
        rack.setRackName(dto.getRackName());
        rack.setLocation(dto.getLocation());
        rack.setCapacity(dto.getCapacity());
        return mapper.map(rackDao.save(rack), RackDTO.class);
    }

    @Override
    public List<RackDTO> getAllRacks() {
        return rackDao.findAll().stream()
                .map(r -> mapper.map(r, RackDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookCopyDTO> getBooksByRack(Long rackId) {
        return bookCopyDao.findByRackId(rackId).stream()
                .map(c -> mapper.map(c, BookCopyDTO.class))
                .collect(Collectors.toList());
    }
}
