$(function () {
    todo.mainScreen.ready();
});

todo.mainScreen = {
    ready : function() {
        this.attachEvent();
    },

    attachEvent: function () {
        $('body').on('click', '#add_project', $.proxy(this.addProjectButtonClicked, this));
    },

    addProjectButtonClicked : function() {
        // already know user. don't care
        // I want to know project no..
    }
};