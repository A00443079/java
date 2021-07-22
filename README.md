# AKHIL ROY - A00443079
# 
# --------------------------------------------------------
# Total 6 EndPoints
# 
# --------------------------------------------------------
# Endpoints that can use either GET or POST Method:
###### /
###### /about
###### /getListOfConfirmationNumbers
# 
# --------------------------------------------------------
# Endpoints that use GET Method:
###### /hotels

Input Parameter: Nothing

Response:
{
‘hotels_list’: [
{ “hotel_name”: &lt;string&gt;
“price” : &lt;Int&gt;
“availability” : &lt;bool&gt;
},
{…..},
{…..},
{…..}
]
}

###### /getReservationDetails?id=0480b7f2-6f44-4383-92ba-8ceaf0aa440e
# 
### The above can also be given without parameters (defaultValue=00000000-0000-0000-0000-000000000000) :
###### /getReservationDetails
# 
# --------------------------------------------------------
# Endpoints that use POST Method:
###### /bookingConfirmation

Input:
{ “hotel_name”: &lt;string&gt;,
“checkin”: &lt;string&gt;,
“checkout”: &lt;string&gt;,
“guests_list”: [
{ “guest_name” : &lt;string&gt;,
“gender”: &lt;string&gt;
},
{……},
{……}
]
}

Response:
{
“confirmation_number” : &lt;string&gt;
}
# --------------------------------------------------------
# Deployed at (URL):
###### http://test-env-1.eba-ax7mps76.us-east-2.elasticbeanstalk.com/
# 
# Thankyou.....
