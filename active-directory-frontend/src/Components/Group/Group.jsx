import "./Group.css"
import React, {useEffect, useState} from 'react'
import Constants from "../../Util/Constants";
import axios from "axios";
import {Link, useParams} from "react-router-dom";

export default function Group() {
  const [group, setGroup] = useState({});
  let {groupId} = useParams();

  useEffect(() => {
    let endpoint = "/groups/" + groupId;
    axios.get(Constants.BACKEND_URL + endpoint)
      .then((response) => response.data)
      .then((data) => {
        setGroup(data)
      });
  }, [groupId]);

  return (
    <div id={"group"}>
      <div className={"pageHeading"}>
        <Link to={`/groups/${groupId}`}>{groupId}</Link>
      </div>
      <Link to={`/groups/${group.id}/users`}>Users</Link>
      <span className={"entityId"}>Id: {group.id}</span>
      <span className={"entityName"}>Name: {group.name}</span>
    </div>

  );
}