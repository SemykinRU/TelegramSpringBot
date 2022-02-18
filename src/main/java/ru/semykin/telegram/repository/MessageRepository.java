package ru.semykin.telegram.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.semykin.telegram.entity.EventEntity;

public interface MessageRepository extends JpaRepository<EventEntity, Long> {

    @Cacheable(cacheNames = "message")
    Page<EventEntity> findAllByEventsNameContainsIgnoreCase(String eventsName, Pageable pageable);

    @Cacheable(cacheNames = "allEvents")
    @Override
    Page<EventEntity> findAll(Pageable pageable);

}
