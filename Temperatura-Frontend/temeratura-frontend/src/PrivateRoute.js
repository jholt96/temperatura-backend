import {
    Route,
    Redirect,
  } from "react-router-dom";
import React from 'react';
import authService from "./Services/authService";


export default function PrivateRoute({ children, ...rest }) {
    return (
      <Route
        {...rest}
        render={({ location }) =>
          this.props.user != null ? (
            children
          ) : (
            <Redirect
              to={{
                pathname: "/login",
              }}
            />
          )
        }
      />
    );
  }