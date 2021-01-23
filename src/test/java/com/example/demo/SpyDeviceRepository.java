package com.example.demo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SpyDeviceRepository implements DeviceRepository {
    public String findById_argument_id;
    public Optional<Device> findById_returnValue = Optional.empty();
    public Boolean findAll_wasCalled = false;
    public List<Device> findAll_returnValue = Collections.emptyList();
    public Device save_argument_entity;
    public String deleteById_argument_id;

    @Override
    public List<Device> findAll() {
        findAll_wasCalled = true;
        return findAll_returnValue;
    }

    @Override
    public List<Device> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends Device> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Device> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Device> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        deleteById_argument_id = s;
    }

    @Override
    public void delete(Device entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Device> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Device> S save(S entity) {
        save_argument_entity = entity;
        return null;
    }

    @Override
    public <S extends Device> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Device> findById(String s) {
        findById_argument_id = s;
        return findById_returnValue;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends Device> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Device> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Device> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Device> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Device> boolean exists(Example<S> example) {
        return false;
    }
}
