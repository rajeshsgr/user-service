package com.nola.raj.user.service.impl;

import com.nola.raj.user.service.UsersService;
import com.nola.raj.user.domain.Users;
import com.nola.raj.user.repository.UsersRepository;
import com.nola.raj.user.service.dto.UsersDTO;
import com.nola.raj.user.service.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Users.
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    /**
     * Save a users.
     *
     * @param usersDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsersDTO save(UsersDTO usersDTO) {
        log.debug("Request to save Users : {}", usersDTO);

        Users users = usersMapper.toEntity(usersDTO);
        users = usersRepository.save(users);
        return usersMapper.toDto(users);
    }

    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UsersDTO> findAll() {
        log.debug("Request to get all Users");
        return usersRepository.findAll().stream()
            .map(usersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one users by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsersDTO> findOne(Long id) {
        log.debug("Request to get Users : {}", id);
        return usersRepository.findById(id)
            .map(usersMapper::toDto);
    }

    /**
     * Delete the users by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Users : {}", id);
        usersRepository.deleteById(id);
    }
}
