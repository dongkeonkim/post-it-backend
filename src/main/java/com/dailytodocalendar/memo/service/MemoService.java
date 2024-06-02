package com.dailytodocalendar.memo.service;

import com.dailytodocalendar.member.dto.MemberDto;
import com.dailytodocalendar.memo.dto.MemoDto;
import com.dailytodocalendar.memo.entity.Memo;
import com.dailytodocalendar.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public List<MemoDto> selectMemoAll(MemberDto memberDto) {
        return memoRepository.findAllByMemberId(memberDto.getId()).stream()
                .map(Memo::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemoDto createMemo(MemoDto memoDto, MemberDto memberDto) {
        memoDto.setMemberId(memberDto.getId());
        memoDto.getTodos().forEach(todoDto -> todoDto.setMemberId(memberDto.getId()));

        return memoRepository.save(memoDto.toEntity()).toDto();
    }

    @Transactional
    public MemoDto updateMemo(MemoDto memoDto, MemberDto memberDto) {
        Memo memo = memoRepository.findByIdAndMemberId(memoDto.getId(), memberDto.getId())
                .orElseThrow(IllegalArgumentException::new);

        memoDto.getTodos().forEach(todoDto -> todoDto.setMemberId(memberDto.getId()));
        memo.update(memoDto);
        memoRepository.save(memo);

        return memo.toDto();
    }

    @Transactional
    public void deleteMemo(Long id, MemberDto memberDto) {
        Memo memo = memoRepository.findByIdAndMemberId(id, memberDto.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(HttpStatus.NOT_FOUND.value()));

        memoRepository.delete(memo);
    }

}
