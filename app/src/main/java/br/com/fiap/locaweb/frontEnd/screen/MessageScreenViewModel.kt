package br.com.fiap.locaweb.frontEnd.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageScreenViewModel : ViewModel(){
    private val _sender = MutableLiveData<String>()
    val senderState: LiveData<String> = _sender

    private val _recipients = MutableLiveData<String>()
    val recipientsState: LiveData<String> = _recipients

    private val _subject = MutableLiveData<String>()
    val subjectState: LiveData<String> = _subject

    private val _body = MutableLiveData<String>()
    val bodyState: LiveData<String> = _body

    private val _senderName = MutableLiveData<String>()
    val senderNameState: LiveData<String> = _senderName

    private val _senderEmail = MutableLiveData<String>()
    val senderEmailState: LiveData<String> = _senderEmail

    private val _read = MutableLiveData<Boolean>()
    val readState: LiveData<Boolean> = _read

    private val _important = MutableLiveData<Boolean>()
    val importantState: LiveData<Boolean> = _important

    private val _favorite = MutableLiveData<Boolean>()
    val favoriteState: LiveData<Boolean> = _favorite

    private val _archived = MutableLiveData<Boolean>()
    val archivedState: LiveData<Boolean> = _archived

    fun onSenderChanged(newSender: String) {
        _sender.value = newSender
    }

    fun onRecipientsChanged(newRecipients: String) {
        _recipients.value = newRecipients
    }

    fun onSubjectChanged(newSubject: String) {
        _subject.value = newSubject
    }

    fun onBodyChanged(newBody: String) {
        _body.value = newBody
    }

    fun onSenderNameChanged(newSenderName: String) {
        _senderName.value = newSenderName
    }

    fun onSenderEmailChanged(newSenderEmail: String) {
        _senderEmail.value = newSenderEmail
    }

    fun onReadStateChanged(newReadState: Boolean) {
        _read.value = newReadState
    }

    fun onImportantStateChanged(newImportantState: Boolean) {
        _important.value = newImportantState
    }

    fun onFavoriteStateChanged(newFavoriteState: Boolean) {
        _favorite.value = newFavoriteState
    }

    fun onArchivedStateChanged(newArchivedState: Boolean) {
        _archived.value = newArchivedState
    }
}