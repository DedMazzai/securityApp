package ru.davydenko.springcourse.securityapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.davydenko.springcourse.securityapp.models.Member;
import ru.davydenko.springcourse.securityapp.repositories.PeopleRepository;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Member member){
        peopleRepository.save(member);
    }
}
