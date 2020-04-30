package com.netflix.playback.data.repo;

import com.netflix.playback.data.dto.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MemberRepository extends CrudRepository<Member, UUID> {

}
