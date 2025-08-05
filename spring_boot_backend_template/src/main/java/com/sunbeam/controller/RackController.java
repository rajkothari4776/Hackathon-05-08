package com.sunbeam.controller;
@RestController
@RequestMapping("/racks")
public class RackController {

    @Autowired
    private RackService rackService;

    @PostMapping
    public ResponseEntity<RackDTO> addRack(@RequestBody RackDTO dto) {
        return ResponseEntity.ok(rackService.addRack(dto));
    }

    @PutMapping("/{rackId}")
    public ResponseEntity<RackDTO> updateRack(@PathVariable Long rackId, @RequestBody RackDTO dto) {
        return ResponseEntity.ok(rackService.updateRack(rackId, dto));
    }

    @GetMapping
    public ResponseEntity<List<RackDTO>> getAllRacks() {
        return ResponseEntity.ok(rackService.getAllRacks());
    }

    @GetMapping("/{rackId}/books")
    public ResponseEntity<List<BookCopyDTO>> getBooksByRack(@PathVariable Long rackId) {
        return ResponseEntity.ok(rackService.getBooksByRack(rackId));
    }
}

