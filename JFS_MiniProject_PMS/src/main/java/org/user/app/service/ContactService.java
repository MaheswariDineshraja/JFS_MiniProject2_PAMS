package org.user.app.service;

import org.user.app.repository.ContactRepository;
import org.user.app.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService
{
    @Autowired
    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }
    public void save(Contact contact)
    {
        contactRepository.save(contact);
    }

}

