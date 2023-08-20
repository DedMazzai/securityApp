package ru.davydenko.springcourse.securityapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.davydenko.springcourse.securityapp.models.Member;
import ru.davydenko.springcourse.securityapp.services.MemberDetailService;


@Component
public class MemberValidator implements Validator {

    private final MemberDetailService memberDetailService;

    @Autowired
    public MemberValidator(MemberDetailService memberDetailService) {
        this.memberDetailService = memberDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    //Лучше написать свой сервис по проверке
    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        try {
            memberDetailService.loadUserByUsername(member.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }
}
