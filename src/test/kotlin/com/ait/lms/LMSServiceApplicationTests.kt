package com.ait.lms

import com.ait.lms.controllers.UserController
import com.ait.lms.models.Student
import com.ait.lms.services.ServiceTier
import com.ait.lms.utils.Utils
import org.springframework.beans.factory.annotation.Autowired


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LMSServiceApplicationTests(
    @Autowired val serviceTier: ServiceTier,
    @Autowired val utils: Utils,
    @Autowired val userController: UserController
) {

//    @Test
//    fun contextLoads() {
//    }

    val student = Student(
        id= null,
        firstName = "Godwin",
        surName = "Duah",
        emailAddress = "breezeexp1@gmail.com",
        phoneNumber = "+2330203021682",
        password = "@emc2.roaM",
        avatar = null,
        header = null,
        isVerified = false,
        createdOn = utils.getCurrentTime(),
        gender = null,
        isActive = false,
        isOnline = false,
        otherNames = null,
        updatedOn = utils.getCurrentTime(),
        dateOrBirth = null,
        emergencyContactNumber = null,
        emergencyContactPerson = null,
        lastOnline = null,
    )

//    private final fun login(): String? {
//        val signIn = SignIn(
//            email = "gentlekobby@gmail.com",
//            password = "@emc2.roaM"
//        )
//        auth0Client.login(signIn)?.drop(16)?.dropLast(1)
//        val authResponse = JSONObject(auth0Client.login(signIn)?.drop(16)?.dropLast(1))
//        print("MFA ${authResponse.getString("mfa_token")}")
//        return authResponse.getString("mfa_token")
//    }
//    val mfaToken = login()
    val mfaToken = "Fe26.2*a60a93*b1af102d2515044106822fb98c2736286e5bddb167333af2345bfb650bf4202c*D8K2JIJ7QsZ-VFW0Z4fpqg*uT-9_e81EVVzhirI3QSrstype3cP2JzF0shwPZqmQW6vnuX7MRvjsRy0PcovI6Q396bymEm1ij4_nfezqyYakzqEx16csyVnc9gPm99fIdadPcQ_socZ3BLLOzjfsn89LCrRQYFJLhnfliEzRrCX-8rf9WDxOK-awMrr1FvHRZXNEB5YAPXlXcL-rLvanEWwwn8_huJ1GrovLB85meeDz9C5r5uITs7EzdXdBizDBbBK31gTFPCGXVc6jVpWUcAJWwkSOAxsawKAVMNpZcBRQFvsnYoVpe6kxIMqksyKOih1Fd5L7LXLiTIT6-YjNbuGighS6It-MS3NcojM-YX6xIoHQZBCJddzTXQ0kxJnPFABNayKgDRdSMwcs7VV9W02xTDP1SFsRll5TV-fatZ0lf-enUS3DknnJ-r3KmGynXzZR_PIvx0-YVi3CYmZXCpWsZXqWNiyUeNLQydJH6GGIxVFcKJ2UPZeBzdlgchz37yMYl91dMD_fLy5IgVS0SWEb3RE_AySW2cO0C-Xx9r6MvZlZWVxJ8RKKaF3PGFB7d69ovte_58X3J4AHVstnmzEKLsKYh_pqSrNKELpLSxMSBkUs17QgsBaMvmBKbGRvQto-ljBnWyyCLpNPhs5CYogj1a-XJJ2NHCFmql3tE_rH2iTq9btZLtMFk3JqZYxo805GFZJJrrLej3ff0n7knBre0abOOBBm4R-7uuqCL4oDlIF5uxw2IBSUBAoQy13Dw7Mtl_bQfot_hf3caxrr9Qb-jlXvk3wKfxica6NdzF9PaT0gbcxCmS4Txn__JeRrih_ZDjHP4osY-TXvdAVIMns28JI6g3uq8DMqt33pK5pEPX3n5h-LzceejmbJ987Mq0XalBd9FIsGm9hVLLOcsJkRvj1ev9cPFVAgRddkQvvocB-ndQYpu0RdFgCO5YcxJXZkpHEVBqKEA8NY1vuI904OAWJ0fsdNsgpDBOLgMBoc9svhgnQHuFzr76p9UPMR2WTi8TUf3PHfjHfg9D_ksw7m1fwv-Sowr3DqTFo9GtyKkgzZZ5YJnBDpxwiQPnvhXoHSbaEtkhdCjxKYjm6ASFjf6C-4YY9iqRyISpDMZJCA8p3ostJwsyZAhxm3PbePlgwdHmQ0OAALrbeqAFZagAWIrRVkBZ_29uRd9tw46_4glZSyHXsYFAkTtKaXlwD866peSOlejon9_mF8h2rh9ro_h37WwQHFrk-9d5pWyYtQkfaVdsaTLoKOEo4R1brj9s*1642891627681*3e521b288b6cda2405de3bcc129babfe77f777f5e6396c48b95a499528d568ba*KgmpiUzojGvJBHxDct9Dg0-T5Vtbcs0mQQiDB_IaFJ0"
//    @Test
//    fun testAuth0SignUp(){
//
//        val auth0SignUpResponse = auth0Client.signUp(student)
//        val responseBody = JSONObject(auth0SignUpResponse?.body)
//        if (auth0SignUpResponse?.statusCode == HttpStatus.OK) {
//            student.password = BCrypt.hashpw(student.password, BCrypt.gensalt(4))
//            student.id = "auth0|${responseBody.get("_id")}"
//            userService.saveMember(student)
//        }
//    }

//    @Test
//    fun testAuth0Login(){
//        val signIn = SignIn(
//            email = "breezeexp1@gmail.com",
//            password = "@emc2.roaM"
//        )
//        auth0Client.login(signIn)?.drop(16)?.dropLast(1)
//        val authResponse = JSONObject(auth0Client.login(signIn)?.drop(16)?.dropLast(1))
//        print("MFA Token :: ${authResponse.getString("mfa_token")}")
//    }

//    @Test
//    fun testAuth0Challenge(){
//        val signIn = SignIn(
//            email = "gentlekobby@gmail.com",
//            password = "@emc2.roaM"
//        )
//        val user = userService.findMemberByEmail(signIn.email)
//        val userMFA = userService.getCourseByCode(user?.id!!).last()
//        auth0Client.challengeMFA(userMFA,signIn)
//    }

//    @Test
//    fun testAuth0Authenticators(){
//        val mfaAuthenticators = JSONArray(auth0Client.authenticators(mfaToken))
//        val authenticator = mfaAuthenticators.getJSONObject(0)
//
//        val course = Course(
//            id = authenticator.getString("id"),
//            authenticatorType = authenticator.getString("authenticator_type"),
//            active = authenticator.getString("active"),
//            oobChannel = authenticator.getString("oob_channel"),
//            name = authenticator.getString("name"),
//            memberId = null
//        )
//
//        println(course.id)
//    }

//    @Test
//    fun testAuth0EnrollMFA(){
//        val response = auth0Client.enrollMFA(mfaToken!!,"+2330244435026")
//        userController.persistUserAuthenticators("auth0|61ec6bdcfd09a90068586e05", mfaToken!!)
//        Assertions.assertEquals(HttpStatus.OK, response?.statusCode)
//    }

//    @Test
//    fun testConfirmMFA() {
//        auth0Client.confirmEnrollment(
//            mfaToken = mfaToken!!,
//            oobCode = "Fe26.2*a60a93*34b4039eca188ad0ccaa56e72de86825d79235c3f19076fddf4439ff09a45ac1*kn-wAFsRlMmkPKy2R6wu4g*EQFzDLVZ6U6A-89IaO7br0EgTQJc3wiBxyKTNYK4-MOlX6nWgFOr3RFqw9J7x_SFoMosn9AzpdX3cNbFOJgEoAEcwO3zshyigyWfBVbVAKmDzZxdZ4S3MVT7CBsfcBS9wubUSZ_gHgt95PQcCwNlg3NYQN0QBtUVVVu7iNLtzq6dIbFmoMktQwKN09iLIH-mj2irKk-9vE_xnpwKj3PBxlMte1yP6wFDh8uvYGkoVrG9lCZAq8YivWExwZGl2B1HNCzsz-n6l1CoQTdZdeKxkOdNMxWF-P4uMtMP09z0fOSuqgTA5acfbiGcp4L2oCDg4xcmDSbhLaT4LpxX_hNUqw*1642891628909*01064fd69fb499060985b465a97cbd4227a49a8469d34e27c7d2e36b3489558d*gKGdfi1iOGdTtiBZ1fMntC-6y-xUvKz8sctlyTjW_Xo",
//            otpCode = "613868"
//        )
//    }

//    @Test
//    fun testAuth0Token(){
//        val signIn = SignIn(
//            email = "breezeexp1@gmail.com",
//            password = "@emc2.roaM"
//        )
////        auth0Client.getToken(signIn, mfaToken!!);
//        auth0Client.getMFAToken(signIn)
//    }


//    @Test
//    fun testAuth0ChangePassword(){
//        auth0Client.changePassword("godwin@bnkability.com")
//    }

//    @Test
//    fun testGetUserByEmail(){
//        val user = userService.findMemberByEmail("breezeexp1@gmail.com")
//        Assertions.assertEquals("breezeexp1@gmail.com", user?.emailAddress)
//    }

//    @Test
//    fun testHubspotCreateContact() {
//        val hubspotResponse =  hubspotClient.createContact(student)
//        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hubspotResponse.statusCode)
//    }
}
