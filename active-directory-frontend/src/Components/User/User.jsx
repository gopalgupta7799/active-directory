import "./User.css"
import React, {useEffect, useState} from 'react'
import Constants from "../../Util/Constants";
import axios from "axios";
import {Link, useParams} from "react-router-dom";

export default function User() {
  const [user, setUser] = useState({});
  let {userId} = useParams();

  useEffect(() => {
    let endpoint = "/users/" + userId;
    axios.get(Constants.BACKEND_URL + endpoint)
      .then((response) => response.data)
      .then((data) => {
        setUser(data)
      });
  }, [userId]);

  return (
    <div id={"user"}>
      <div className={"pageHeading"}>
        <Link to={`/users/${userId}`}>{userId}</Link>
      </div>
      <Link to={`/users/${user.id}/groups`}>Groups</Link>
      <span className={"entityId"}>Id: {user.id}</span>
      <span className={"entityName"}>Name: {user.name}</span>
      <span className={"entityEmail"}>Email: {user.email}</span>
    </div>

  );
}