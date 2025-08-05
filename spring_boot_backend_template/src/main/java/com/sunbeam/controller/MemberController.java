package com.sunbeam.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {


    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.addMember(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateMember(@PathVariable Long id) {
        memberService.deactivateMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Boolean paid) {
        return ResponseEntity.ok(memberService.getAllMembers(active, paid));
    }
}

