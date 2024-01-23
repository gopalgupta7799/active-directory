import './Groups.css'
import React, {useEffect, useState} from 'react'
import Constants from "../../Util/Constants";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Table from "../Table/Table";
import Pagination from "../Pagination/Pagination";
import PopUp from "../PopUp/PopUp";

export default function Groups() {
  const [pageNumber, setPageNumber] = useState(0);
  const [groups, setGroups] = useState([]);
  const [pages, setPages] = useState([]);
  const [showPopUp, setShowPopUp] = useState(false);
  const [name, setName] = useState("");
  let {userId} = useParams();

  useEffect(() => {
    let endpoint = "";
    if (userId !== undefined) {
      endpoint += "/users/" + userId;
    }
    endpoint += "/groups";
    axios.get(Constants.BACKEND_URL + endpoint, {
      headers: {
        "pageNumber": pageNumber
      }
    })
      .then((response) => response.data)
      .then((data) => {
        let temp = [];
        for (let i = 0; i < data.totalPages; i++) {
          temp.push(i + 1);
        }
        setPages([...temp])
        setGroups(data.content)
      });
  }, [userId, pageNumber]);

  function submitForm(e) {
    e.preventDefault();
    setShowPopUp(false);
    let formData = {};
    formData["name"] = name;
    console.log(formData);
    axios({
      // Endpoint to send files
      url: Constants.BACKEND_URL + "/users",
      method: "POST",
      headers: {},
      // Attaching the form data
      data: formData,
    })
      // Handle the response from backend here
      .then((res) => {
        console.log(res);
      })
      // Catch errors if any
      .catch((err) => {
        console.log(err);
      });
    setName('');
  }

  return (
    <div id={"groups"}>
      <div className={"pageHeading"}>
        {userId !== undefined
          ? <><Link to={`/users/${userId}`}>{userId}</Link> | </> : ""}
        Groups
      </div>
      <div className={"optionsMenu"}>
        <span onClick={() => setShowPopUp(true)}>Add</span>
        <PopUp showPopUp={showPopUp} setShowPopUp={setShowPopUp} data={
          <form className={"popUpForm"} onSubmit={e => submitForm(e)}>
            <div>
              <div>
                <legend>Name</legend>
                <input name="name" value={name} onChange={(e) =>
                  setName(e.target.value)}/>
              </div>
            </div>
            <input type={"submit"} value={"Submit"}/>
          </form>
        }/>
      </div>
      <Table
        columns={{"Id": "id", "Name": "name"}}
        rows={groups}
        type={"groups"}
      />
      <Pagination
        pages={pages}
        pageNumber={pageNumber}
        setPageNumber={setPageNumber}
      />
    </div>

  );
}