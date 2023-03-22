package com.hpi.tpc.ui.views.notes;

import com.hpi.tpc.data.entities.*;
import com.hpi.tpc.ui.views.baseClass.*;
import com.studerw.tda.client.*;
import com.studerw.tda.model.quote.*;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.spring.annotation.*;
import java.util.*;
import lombok.*;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * handles state of application
 * contains objects representing the data
 * provides ways to query and change the data
 * responds to requests from View and instructions from Controller
 */
@UIScope
@VaadinSessionScope
@Component
public class NotesModel
    extends MVCModelBase
{

    @Setter @Getter private NoteModel noteModel;
    @Getter private List<NoteModel> dataProvider;
    @Getter private final BeanValidationBinder<NoteModel> binder;

    private final Properties props;
    private final HttpTdaClient httpTdaClient;
    @Getter private Quote quote;
    @Getter @Setter private Boolean isSave;
    @Getter @Setter private Boolean isAdd;

    public NotesModel()
    {
        this.binder = new BeanValidationBinder<>(NoteModel.class);
        this.props = new Properties();
        props.setProperty("tda.client_id", "VYEUORVKFCJSAZG9TBJOGSQZX8PKXWZB");
        props
            .setProperty("tda.token.refresh", "ixs1FhJY2f7uhCRjK8a+Brw0XQyAjLV4Q/H5QCDz00M3B3ep0/bBnJNaiFanWnp1f3Kpg9lNUqfHlE1bQm8XhEivX/Kbo2h/lR7uQ28xCEAYBS/xetpQyvT4DL1WtA3wZtNdDiPRwiTHP63pnf+wK46iKal2MlaLLebpUXLNqPWVBqc43/NpukM/8UX2dZiW7Xl/Oh/lJKuxvH8obosWVPdc4Ol0VVQ5ff9JL2o0ZovfDs01P55UJfhv3f8ZAImUxa3zrjlnR9JwrIqq0QK6tITO0ysanVB+dhe+lzb0f9nqc+mqxEB6t55+K0413Pyl5uzEhSh5Owt39sRy/FGEAf+JkwSWgYxJV3u0VcMgW9/FZZy1s27126bYhEtJ35AhwXN4xLY/InT0WgblGAybBsYh5BNksZHy52ZRiFgGvLRszvrshoEQu8hyKby100MQuG4LYrgoVi/JHHvlH/wBtHqoauMnRWdARXR4HRyF7vk3BOX/jvFTmHSXk4nP9Iv79q+t2Wt71/E/kT6ORlOjHVYNRg3Roo4X0SBj9yb5fFzvwm8G/4X+nhM7nScW69tPsCzqDNdDecvnvp47ma9EEW/L2vLWXdVjcLR1CJrS6XHUb2SOZiAKJb8MErUlfHgeL0eNRNW5tSbgh62uywdJigmwB/lmHdG537sMIjyX1yIYmkF0L6Gtx0qjS7/nB+5U9ZFyeSfwSEk5maHJOXalCTWyCecEqMpEDAUhVYmkXfMsUbWbslXlfXI9gS7n/QyZzR6Tz8jbKCVAd9S8f2BP/kJhGGSl702Y9OlXIlxZk5pDIcZElX2TjGAbmr6eqwN+UIKfYxzI2lzWo/EdA9L8Yks6Iag7FDDTFvhNYnQ01N0AsKFWoBzz2buHkDOnegsshbhM5vxZ5Wg=212FD3x19z9sWBHDJACbC00B75E");
        props.setProperty("tda.account.id", "865-837053");
//        props.setProperty("tda.url", CMOfxDirectModel.getFIMODELS().
//            get(0).getHttpHost());
        props.setProperty("tda.debug.bytes.length", "-1");

        this.httpTdaClient = new HttpTdaClient(props);

        this.quote = null;
        this.isSave = false;
        this.isAdd = false;
    }

    public Quote getTickerInfo(String ticker)
    {
        return this.quote = this.httpTdaClient.fetchQuote(ticker);
    }

    public void getPrefs(String prefPrefix)
    {
//        this.prefsController.setDefaults("PortfolioTrack");
//        this.prefsController.readPrefsByPrefix("PortfolioTrack");
//        this.selectedTrackActive = this.prefsController
//            .getPref("PortfolioTrackActive").equals("Yes");
//        this.selectedTrackOpen = this.prefsController
//            .getPref("PortfolioTrackOpen").equals("Yes");
    }

    public void writePrefs()
    {
//        //preferences, update the hashmap, then write to database
//        this.prefsController.setPref("PortfolioTrackActive",
//            selectedTrackActive ? "Yes" : "No");
//        this.prefsController.setPref("PortfolioTrackOpen",
//            selectedTrackOpen ? "Yes" : "No");
//        
//        this.prefsController.writePrefsByPrefix("PortfolioTrack");
    }

//    public void setSelectNoteModel(NoteModel noteModel)
//    {
//        //without the session variable, this could be simple setter
//        this.setSelectedNoteModel(noteModel);
//    }
    /**
     * Save the note to the database
     * need to come in with the NoteModel from the form.
     *
     * @param isArchive: true if archiving a note
     */
    public void saveUpdate(Boolean isArchive)
    {
        //not hit
        NoteModel tempNoteModel;

        if (isArchive)
        {
            this.serviceTPC.AppTracking("TPC:Notes:Edit:Archive");
        } else
        {
            this.serviceTPC.AppTracking("TPC:Notes:Edit:Save");
        }

        //get data from the form
        tempNoteModel = new NoteModel(
            //these are key fields
            this.noteModel.getJoomlaId(),
            //null for add
            this.noteModel.getTStamp(),
            //uppercase if a ticker
            this.noteModel.getTicker(),
            this.noteModel.getIPrice(),
            this.noteModel.getDescription(),
            this.noteModel.getNotes(),
            this.noteModel.getUnits(),
            NoteModel.setActionInt(this.noteModel.getAction()).toString(),
            NoteModel.setTriggerInt(this.noteModel.getTriggerType()).toString(),
            this.noteModel.getTrigger(),
            this.noteModel.getActive(),
            this.noteModel.getDateEntered()
        );

        this.serviceTPC.updateNote(tempNoteModel);
    }

    /**
     * Save the note to the database
     * need to come in with the NoteModel from the form.
     */
    public void saveAdd()
    {
        NoteModel tempNoteModel;

        this.serviceTPC.AppTracking("TPC:Notes:Add:Save");

        tempNoteModel = new NoteModel(
            //these are key fields
            this.noteModel.getJoomlaId(),
            //null for add
            this.noteModel.getTStamp(),
            //uppercase if a ticker
            this.noteModel.getTicker(),
            this.noteModel.getIPrice(),
            this.noteModel.getDescription(),
            this.noteModel.getNotes(),
            this.noteModel.getUnits(),
            NoteModel.setActionInt(this.noteModel.getAction()).toString(),
            NoteModel.setTriggerInt(this.noteModel.getTriggerType()).toString(),
            this.noteModel.getTrigger(),
            this.noteModel.getActive(),
            this.noteModel.getDateEntered()
        );

        this.serviceTPC.saveNote(tempNoteModel);
    }

    public void getData(String subset)
    {
        List<NoteModel> aList;

        aList = this.serviceTPC.getByJId(subset);

        this.dataProvider = aList;
    }

    @Override
    public void writePrefs(String prefix)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
