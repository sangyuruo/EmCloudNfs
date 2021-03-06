package com.emcloud.nfs.service.impl;

import com.emcloud.nfs.security.SecurityUtils;
import com.emcloud.nfs.service.NotifyLogService;
import com.emcloud.nfs.domain.NotifyLog;
import com.emcloud.nfs.repository.NotifyLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


/**
 * Service Implementation for managing NotifyLog.
 */
@Service
@Transactional
public class NotifyLogServiceImpl implements NotifyLogService{

    private final Logger log = LoggerFactory.getLogger(NotifyLogServiceImpl.class);

    private final NotifyLogRepository notifyLogRepository;

    public NotifyLogServiceImpl(NotifyLogRepository notifyLogRepository) {
        this.notifyLogRepository = notifyLogRepository;
    }

    /**
     * Save a notifyLog.
     *
     * @param notifyLog the entity to save
     * @return the persisted entity
     */
    @Override
    public NotifyLog save(NotifyLog notifyLog) {
        log.debug("Request to save NotifyLog : {}", notifyLog);
        notifyLog.setCreatedBy(SecurityUtils.getCurrentUserLogin());
        notifyLog.setCreateTime(Instant.now());
        notifyLog.setUpdateTime(Instant.now());
        return notifyLogRepository.save(notifyLog);
    }

    /**
     * Update a notifyLog.
     *
     * @param notifyLog the entity to update
     * @return the persisted entity
     */
    @Override
    public NotifyLog update(NotifyLog notifyLog) {
        log.debug("Request to save NotifyLog : {}", notifyLog);
        notifyLog.setUpdateTime(Instant.now());
        return notifyLogRepository.save(notifyLog);
    }

    /**
     *  Get all the notifyLogs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NotifyLog> findAll(Pageable pageable) {
        log.debug("Request to get all NotifyLogs");
        return notifyLogRepository.findAll(pageable);
    }

    /**
     *  Get one notifyLog by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NotifyLog findOne(Long id) {
        log.debug("Request to get NotifyLog : {}", id);
        return notifyLogRepository.findOne(id);
    }

    /**
     *  Delete the  notifyLog by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotifyLog : {}", id);
        notifyLogRepository.delete(id);
    }
}
