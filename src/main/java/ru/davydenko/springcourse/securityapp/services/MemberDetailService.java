package ru.davydenko.springcourse.securityapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.davydenko.springcourse.securityapp.models.Member;
import ru.davydenko.springcourse.securityapp.repositories.PeopleRepository;
import ru.davydenko.springcourse.securityapp.security.MemberDetails;

import java.util.Optional;

@Service
public class MemberDetailService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public MemberDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = peopleRepository.findByUsername(username);

        if (member.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new MemberDetails(member.get());
    }
}
