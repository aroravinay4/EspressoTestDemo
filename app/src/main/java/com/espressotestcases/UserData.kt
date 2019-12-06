package com.espressotestcases

object  UserData {


    fun getData() :ArrayList<UserModel> {
        val users = ArrayList<UserModel>()

        //adding some dummy data to the list
        users.add(UserModel("Max"))
        users.add(UserModel("Monty"))
        users.add(UserModel("Rocky"))
        users.add(UserModel("Yash"))
        users.add(UserModel("Max"))
        users.add(UserModel("Monty"))
        users.add(UserModel("Rocky"))
        users.add(UserModel("Yash"))
        users.add(UserModel("Max"))
        users.add(UserModel("Monty"))
        users.add(UserModel("Rocky"))
        users.add(UserModel("Yash"))
        users.add(UserModel("Rocky"))
        users.add(UserModel("Yash"))
        users.add(UserModel("Max"))


        return users


    }
}