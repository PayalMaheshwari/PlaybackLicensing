package com.netflix.playback.data.repo;

import com.netflix.playback.data.dto.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContentRepository extends CrudRepository<Content, UUID> {

}
