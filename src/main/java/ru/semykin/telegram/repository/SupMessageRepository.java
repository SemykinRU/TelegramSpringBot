package ru.semykin.telegram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semykin.telegram.entity.SupMessageEntity;
import ru.semykin.telegram.util.TypeMessageEnum;

@Repository
public interface SupMessageRepository extends JpaRepository<SupMessageEntity, Long> {

    SupMessageEntity findByType(TypeMessageEnum help);

}
