package com.nus.iss.edu.sg.AddressBook.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.nus.iss.edu.sg.AddressBook.model.Contact;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Contacts {
    private Logger logger = Logger.getLogger(Contacts.class.getName());
    public void saveContact(Contact contact, Model model, ApplicationArguments applicationArgs){
        String dataFileName = contact.getId();
        Set<String> optionNames = applicationArgs.getOptionNames();
        String[] optionNamesArray = optionNames.toArray(new String[optionNames.size()]);
        List<String> optionValues = applicationArgs.getOptionValues(optionNamesArray[0]);
        String[] optionValuesArray = optionValues.toArray(new String[optionValues.size()]);
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(optionValuesArray[0] + "/" + dataFileName, Charset.forName("UTF-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());

        }catch(IOException e){
            logger.log(Level.WARNING, e.getMessage());
        }finally{
            printWriter.close();
            try {
                fileWriter.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        
        }
        model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), 
                        contact.getEmail(), contact.getPhoneNumber()));
    }

    public void getContactById(Model model, String contactId, ApplicationArguments applicationArgs){
        Set<String> optionNames = applicationArgs.getOptionNames();
        String[] optionNamesArray = optionNames.toArray(new String[optionNames.size()]);
        List<String> optionValues = applicationArgs.getOptionValues(optionNamesArray[0]);
        String[] optionValuesArray = optionValues.toArray(new String[optionValues.size()]);
        Contact cResp = new Contact();
        try{
            Path filePath = new File(optionValuesArray[0] + "/" + contactId).toPath();
            Charset charset = Charset.forName("UTF-8");
            List<String> stringListValue = Files.readAllLines(filePath, charset);
            cResp.setName(stringListValue.get(0));
            cResp.setEmail(stringListValue.get(1));
            cResp.setPhoneNumber(stringListValue.get(2));
            cResp.setId(contactId);
        } catch (IOException e){
            logger.log(Level.WARNING, e.getMessage());
        }

        model.addAttribute("contact", cResp);

    }
}
