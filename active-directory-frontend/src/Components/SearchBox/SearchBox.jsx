import './SearchBox.css'
import React, {useEffect, useState} from 'react'
import axios from "axios";
import Constants from "../../Util/Constants";

export default function SearchBox() {
  const [searchString, setSearchString] = useState("");
  useEffect(() => {
    if (searchString.length > 3)
    axios.get(Constants.BACKEND_URL + "/users",
        {
          headers: {
            "pageNumber": 0
          }
        })
        .then(response => response.data)
        .then(data => console.log(data));
  }, [searchString]);


  return (
      <input id={"searchBox"} placeholder={"Search in AD"} value={searchString}
             onChange={(e) => setSearchString(e.target.value)}/>
  )
}


