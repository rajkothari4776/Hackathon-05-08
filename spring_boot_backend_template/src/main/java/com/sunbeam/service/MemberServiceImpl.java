package com.sunbeam.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;
    private ModelMapper mapper;

    @Override
    public MemberDTO addMember(MemberDTO dto) {
        Member member = mapper.map(dto, Member.class);
        member.setMembershipDate(LocalDate.now());
        member.setIsActive(true);
        member.setIsPaid(false);
        return mapper.map(memberDao.save(member), MemberDTO.class);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = memberDao.findById(id).orElseThrow();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());
        return mapper.map(memberDao.save(member), MemberDTO.class);
    }

    @Override
    public void deactivateMember(Long id) {
        Member member = memberDao.findById(id).orElseThrow();
        member.setIsActive(false);
        memberDao.save(member);
    }

    @Override
    public List<MemberDTO> getAllMembers(Boolean active, Boolean paid) {
        List<Member> members;

        if (active != null && paid != null)
            members = memberDao.findByIsActiveAndIsPaid(active, paid);
        else if (active != null)
            members = memberDao.findByIsActive(active);
        else if (paid != null)
            members = memberDao.findByIsPaid(paid);
        else
            members = memberDao.findAll();

        return members.stream()
                .map(m -> mapper.map(m, MemberDTO.class))
                .collect(Collectors.toList());
    }
}
