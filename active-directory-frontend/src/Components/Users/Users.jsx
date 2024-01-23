import './Users.css'
import React, {useEffect, useState} from 'react'
import Constants from "../../Util/Constants";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Table from "../Table/Table";
import Pagination from "../Pagination/Pagination";
import PopUp from "../PopUp/PopUp";

export default function Users() {
  const [pageNumber, setPageNumber] = useState(0);
  const [users, setUsers] = useState([]);
  const [pages, setPages] = useState([]);
  const [showPopUp, setShowPopUp] = useState(false);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  let {groupId} = useParams();

  useEffect(() => {
    let endpoint = "";
    if (groupId !== undefined) {
      endpoint += "/groups/" + groupId;
    }
    endpoint += "/users";
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
        setUsers(data.content)
      });
  }, [groupId, pageNumber]);

  function submitForm(e) {
    e.preventDefault();
    setShowPopUp(false);
    let formData = {};
    formData["name"] = name;
    formData["email"] = email;
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
    setEmail('');

  }

  return (
    <div id={"users"}>
      <div className={"pageHeading"}>
        {groupId !== undefined
          ? <><Link to={`/groups/${groupId}`}>{groupId}</Link> | </> : ""}
        Users
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
              <div>
                <legend>Email</legend>
                <input name="email" value={email} onChange={(e) =>
                  setEmail(e.target.value)}/>
              </div>
            </div>
            <input type={"submit"} value={"Submit"}/>
          </form>
        }/>
      </div>
      <Table
        columns={{"Id": "id", "Name": "name", "Email": "email"}}
        rows={users}
        type={"users"}
      />
      <Pagination
        pages={pages}
        pageNumber={pageNumber}
        setPageNumber={setPageNumber}
      />
    </div>

  )
    ;
}