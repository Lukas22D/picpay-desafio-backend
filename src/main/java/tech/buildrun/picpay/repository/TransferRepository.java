package tech.buildrun.picpay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.buildrun.picpay.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

}
