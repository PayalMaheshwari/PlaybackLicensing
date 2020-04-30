package com.netflix.playback.data.repo;

import com.netflix.playback.data.dto.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeviceRepository extends CrudRepository<Device, UUID> {

}
