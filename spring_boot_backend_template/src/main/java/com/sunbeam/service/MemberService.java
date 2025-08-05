package com.sunbeam.service;

public interface MemberService {
    MemberDTO addMember(MemberDTO dto);
    MemberDTO updateMember(Long id, MemberDTO dto);
    void deactivateMember(Long id);
    List<MemberDTO> getAllMembers(Boolean active, Boolean paid);
}

