package com.hpi.tpc.ui.views.notes.notesAdd;

import com.hpi.tpc.ui.views.notes.notesAddEdit.*;
import com.vaadin.flow.spring.annotation.*;
import javax.annotation.*;

/**
 * makes direct request for data from model
 * does not change data;
 * user actions are sent to the controller
 * dumb, just builds the view
 */
@UIScope
@VaadinSessionScope
@org.springframework.stereotype.Component
public class NotesAddFormVL
    extends NotesAddEditFormAbstractVL
{

//    @Autowired private NotesModel notesModel;
    public NotesAddFormVL()
    {
        this.addClassName("notesEditFormVL");
    }

    @PostConstruct
    private void construct()
    {
        super.buildForm("Edit a note ...");
    }

    @Override
    public void doLayout()
    {
        super.doLayout();
    }
}
